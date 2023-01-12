package western.covid.project.login.proxy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import western.covid.project.model.User;
import western.covid.project.utils.PathTools;
import western.covid.project.utils.ResponseResult;


/**
 * A class implements IUserDao interface. In this scenario, we choose local json file as database.
 * @author Tianci Du
 * @version 1.0
 */
public class UserDaoImpl implements IUserDao{


	/**
	 * A method is to verify the user input. Simply compare the user name and password with data stored
	 * in the json file. Using gson library to parse data retrieved from json file.
	 * @param name
	 * @param password
	 * @return ResponseResult
	 */
	@Override
	public ResponseResult login(String name, String password) {
		// TODO Auto-generated method stub
		
		try {
			File file=new File(PathTools.databaseFilePath("database.json"));
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(file)));

			StringBuffer stringBuffer = new StringBuffer();
			String temp = "";
			
			while ((temp = bufferedReader.readLine()) != null) {
				stringBuffer.append(temp);
			}
			String userStr = stringBuffer.toString();
			Gson gson = new Gson();
			User user = gson.fromJson(userStr,User.class);
			String userName = user.getName().trim();
			String userPassword=user.getPassword().trim();
			System.out.println("name:"+name+","+userPassword+","+name+":"+password);
			if(name.trim().equals(userName)&&userPassword.trim().equals(password)) {
				return new ResponseResult(200,"login success");
			}
			return null;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
