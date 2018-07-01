package springboot.configuration.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.StringTypeHandler;

public class CustomStringTypeHandler extends StringTypeHandler {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter);
	}
	
	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int targetIndex = 0;
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			if (columnName.equals(rs.getMetaData().getColumnName(i))) {
				targetIndex = i;
			}
		}
		
		if ("TIMESTAMP".equals(rs.getMetaData().getColumnTypeName(targetIndex))) {
			return rs.getString(columnName).substring(0, 11).replaceAll("-", "");
		} else {
			return rs.getString(columnName);
		}
	}
	
	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		if ("TIMESTAMP".equals(rs.getMetaData().getColumnTypeName(columnIndex))) {
			return rs.getString(columnIndex).substring(0, 11).replaceAll("-", "");
		} else {
			return rs.getString(columnIndex);
		}
	}
	
	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		if ("TIMESTAMP".equals(cs.getMetaData().getColumnTypeName(columnIndex))) {
			return cs.getString(columnIndex).substring(0, 11).replaceAll("-", "");
		} else {
			return cs.getString(columnIndex);
		}
	}
}
