import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import com.mysql.jdbc.Driver;
public class JdbcDemo {
public static void main(String[] args) {
Connection conn = null;
try {
// 加载mysql驱动程序
Class.forName("com.mysql.jdbc.Driver");
// 连接localhost上的mysql,并指定使用test数据库，用户名为root,密码�?**
conn = DriverManager.getConnection("jdbc:mysql://10.46.179.209:3308/oozie", "oozie", "oozie");
if (!conn.isClosed()) {
System.out.println("数据库连接成功！"); //验证是否连接成功
}
Statement statement = conn.createStatement();
//查询数据
ResultSet rs = statement.executeQuery("select count(*) from tech_sys_user");
//输出结果集（类似.net中的DataSet/DataTable�?
while (rs.next()) {
System.out.println("id=" + rs.getInt("id") + ",name=" +
rs.getString("Name"));
}
rs.close();
} catch (Exception e) {
e.printStackTrace();
} finally {
if (conn != null) {
try {
conn.close();
conn = null;
} catch (Exception e) {
e.printStackTrace();
}
}
}
}
}
