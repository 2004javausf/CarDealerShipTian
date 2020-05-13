import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.revature.daoimpl.DAOImpl;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
class login {

	@Test
	void loginTest() {
		DAOImpl di = new DAOImpl();
		try {
			di.insertCustomer("jjj", 888);
			Assert.assertEquals(di, 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
