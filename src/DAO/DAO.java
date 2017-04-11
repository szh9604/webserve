package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Super_hao on 2017/4/9.
 */
public class DAO {
    // insert update delete操作
    public void update(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.releaseDB(null, preparedStatement, connection);
        }
    }

    //查询多条记录，返回对应的集合
    public <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {
        List<T> list=null;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            list = new ArrayList<T>();
            connection=JDBCTools.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                // 准备一个Map<别名，存放的列>
                Map<String,Object> values=new HashMap<String,Object>();

                //得到ResultSetMetaData对象，得到结果集有多少列
                ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
                int columnCount=resultSetMetaData.getColumnCount();

                //得到每一列的别名,和Value
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel=resultSetMetaData.getColumnLabel(i+1);
                    Object columnValue=resultSet.getObject(i+1);
                    //填充Map对象
                    values.put(columnLabel,columnValue);
                }

                //用反射创建Class对应的对象
                T object=clazz.newInstance();

                //遍历Map对象，用反射填充对象的属性值
                for (Map.Entry<String,Object> entry:values.entrySet()){
                    String propertyName=entry.getKey();
                    Object value=entry.getValue();

                    ReflectionUtils.setFieldValue(object,propertyName,value);
                }
                list.add(object);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCTools.releaseDB(resultSet,preparedStatement,connection);
        }

        return list;
    }

    //返回一条记录
    public <T> T get(Class<T> clazz, String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        T object= null;

        try {
            connection = JDBCTools.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                // 准备一个Map<别名，存放的列>
                Map<String,Object> values=new HashMap<String,Object>();

                //得到ResultSetMetaData对象，得到结果集有多少列
                ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
                int columnCount=resultSetMetaData.getColumnCount();

                //得到每一列的别名,和Value
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel=resultSetMetaData.getColumnLabel(i+1);
                    Object columnValue=resultSet.getObject(i+1);
                    //填充Map对象
                    values.put(columnLabel,columnValue);
                }

                //用反射创建Class对应的对象
                object=clazz.newInstance();

                //遍历Map对象，用反射填充对象的属性值
                for (Map.Entry<String,Object> entry:values.entrySet()){
                    String propertyName=entry.getKey();
                    Object value=entry.getValue();

                    ReflectionUtils.setFieldValue(object,propertyName,value);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCTools.releaseDB(resultSet,preparedStatement,connection);
        }
        return object;
    }

    //返回某条记录的某个字段或一个统计值（例如总共多少条记录）
    public <E> E getForValue(String sql, Object... args) {

        return null;
    }
}
