package com.laundry_m.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.laundry_m.mvc.vo.BookLine;
import com.laundry_m.mvc.vo.PayLog;
import com.laundry_m.mvc.vo.Users;

import util.DbUtil;

public class UsersDaoImpl implements UsersDao{

	@Override
	public int insertUser(Users users) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUser(Users users) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	/** 
	 * 로그인
	 * @param : User user
	 * @return : User user
	 * */
	@Override
	public Users loginUser(Users users) throws SQLException {
		SqlSession session = null;
		Users loginUser = null;
		try {
			session = DbUtil.getSession();
			// id pw 일치하면
			loginUser = session.selectOne("usersMapper.loginUser", users);
		} finally {
			DbUtil.sessionClose(session);
		}
		return loginUser;
	}

	@Override
	public List<Users> selectAllUser() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> selectByUserId(Users users) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> selectByUserType(Users users) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
