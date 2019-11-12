package com.roc.callback;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultCallback {
	void call(ResultSet resultSet) throws SQLException;
}
