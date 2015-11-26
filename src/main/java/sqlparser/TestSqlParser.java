package sqlparser;

import java.io.BufferedWriter;

import com.foundationdb.sql.parser.SQLParser;
import com.foundationdb.sql.parser.StatementNode;


/**
 * http://blog.csdn.net/isea533/article/details/38361911
 * @author angilin
 *
 */
public class TestSqlParser {
	
    public static void main(String[] args) throws Exception {
        SQLParser parser = new SQLParser();
        StatementNode stmt = parser.parseStatement(
                "select a.userid,a.username,a.password " +
                "from sys_user a inner join sys_dep b on a.userid=b.userid   where 1=1 and  a.username = 'isea533'  order by a.userid");
        stmt.treePrint();
        
        
    }


}
