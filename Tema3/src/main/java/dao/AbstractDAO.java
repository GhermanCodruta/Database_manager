package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    protected String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(";");
        return sb.toString();
    }


    protected String createInsertQuery(){
        StringBuilder sb = new StringBuilder();
        Field[] fields = type.getDeclaredFields();
        int fieldCount = fields.length;

        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append("(");

        for(int i = 0; i < fieldCount - 1; i++){
            sb.append(fields[i].getName());
            sb.append(",");
        }
        //the last field does not require a comma separator
        sb.append(fields[fieldCount - 1].getName());
        sb.append(")");

        sb.append(" VALUES ");
        sb.append("(");

        for(int i = 1; i <= fieldCount - 1; ++i){
            sb.append("?, ");
        }
        sb.append("?");

        sb.append(")");
        System.out.println(sb.toString());
        return sb.toString();
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    protected String createDeleteQuery(){
        StringBuilder sb = new StringBuilder();
        Field[] fields = type.getDeclaredFields();

        sb.append("DELETE ");
        sb.append("FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");
        sb.append(fields[0].getName());
        sb.append(" = ?");

        return sb.toString();
    }

    protected String createUpdateQuery(){
        StringBuilder sb = new StringBuilder();
        Field[] fields = type.getDeclaredFields();
        int fieldCount = fields.length;

        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        for(int i = 1; i < fieldCount - 1; ++i){
            sb.append(fields[i].getName());
            sb.append(" = ? , ");
        }
        //the last field does not require a comma separator

        sb.append(fields[fieldCount - 1].getName());
        sb.append(" = ?");

        sb.append(" WHERE ? = ");
        sb.append(fields[0].getName());
        System.out.println(fields[0]);



        return sb.toString();
    }


}
