package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en_pirate.Gangway;
import org.junit.Assert;
import utilities.DBUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Question1DBStepDefs {

    private static int count = 0;

    @Given("{string} {string} {string} {string} {string} {string} should match with the DB record")
    public void  customer_id_first_name_last_name_city_country_total_amount_should_match_with_the_DB_record(String customer_id, String firstName,String lastName, String city, String country, String sum) throws SQLException {
        String query = "SELECT c.customer_id, c.first_name, c.last_name, ct.city, co.country,SUM(p.amount)\n" +
                "FROM payment p\n" +
                "INNER JOIN customer c \n" +
                "ON c.customer_id = p.customer_id\n" +
                "INNER JOIN address a \n" +
                "ON a.address_id = c.address_id\n" +
                "INNER JOIN city ct\n" +
                "ON ct.city_id = a.city_id\n" +
                "INNER JOIN country co\n" +
                "ON co.country_id = ct.country_id\n" +
                "GROUP BY c.customer_id, ct.city_id,co.country\n" +
                "ORDER BY SUM(amount) DESC\n" +
                "LIMIT 5;\n" +
                "\n";

        List<Map<String, Object>> resultMap = DBUtils.getQueryResultMap(query);
        Map<String, Object> map = resultMap.get(count++);

        Assert.assertEquals(map.get("customer_id") + "", customer_id);
        Assert.assertEquals(map.get("first_name"), firstName);
        Assert.assertEquals(map.get("last_name"), lastName);
        Assert.assertEquals(map.get("city"), city);
        Assert.assertEquals(map.get("country"), country);
        Assert.assertEquals(map.get("sum")+"", sum);
    }
}
