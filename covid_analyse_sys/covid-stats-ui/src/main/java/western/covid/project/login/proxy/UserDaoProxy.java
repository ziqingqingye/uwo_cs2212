package western.covid.project.login.proxy;

import western.covid.project.utils.ResponseResult;

/**
 * A proxy class which implements IUserDao interface is to make program more flexible.
 * Using proxy design pattern to make program changed without affecting the design or
 * the implementation of existing ones.
 * @author Tianci Du
 * @version 1.0
 */
public class UserDaoProxy implements IUserDao{

	private IUserDao target;
	
	public UserDaoProxy(IUserDao target) {
		this.target=target;
	}


	/**
	 * Enhance the function of login method.
	 * @param name
	 * @param password
	 * @return
	 */
	@Override
	public ResponseResult login(String name, String password) {
		// TODO Auto-generated method stub
		if(name==null||name.equals("")||password==null||password.equals("")) {
			return null;
		}
		
		ResponseResult result=target.login(name, password);
		return result;
	}

}
