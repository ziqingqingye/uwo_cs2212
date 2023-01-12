package western.covid.project.login.proxy;

import western.covid.project.utils.ResponseResult;

/**
 * A interface for login the COVID-19 STATS System. A class that is interested in
 * login system implements this interface.
 * @author Tianci Du
 * @version 1.0
 */
public interface IUserDao {

	/**
	 *
	 * @param name
	 * @param password
	 * @return
	 */
	ResponseResult login(String name, String password);

}
