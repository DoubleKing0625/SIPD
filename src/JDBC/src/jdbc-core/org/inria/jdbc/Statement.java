package org.inria.jdbc;

/**
 * <P>A Statement object is used for executing a static SQL statement
 * and obtaining the results produced by it.
 *
 * <P>Only one ResultSet per Statement can be open at any point in
 * time. Therefore, if the reading of one ResultSet is interleaved
 * with the reading of another, each must have been generated by
 * different Statements. All statement execute methods implicitly
 * close a statment's current ResultSet if an open one exists.
 *
 * @see Connection#createStatement
 * @see ResultSet
 */

/*=============================================================================

 Name: Statement.java

 Abs:  Implements the interface java.sql.Statement

 Auth: 18-09-2007, Kevin JACQUEMIN (KJ):
 Rev:  14-10-2007, Kevin JACQUEMIN (KJ):

 =============================================================================*/

import java.sql.SQLException;

public class Statement implements java.sql.Statement
{
	java.sql.ResultSet res;

	private int autoGeneratedIdGlobal;
	private final static byte[] id_buffer = Util.makeTransientByteArray( 4 );
	private final static byte[] id_buffer_genkey = Util.makeTransientByteArray( 8 );

	static byte[] plan_params = Util.makeTransientByteArray( Macro.PLAN_PARAMS_MAX_SIZE );
	// byte array to store the parameters
	// type_in_db1 (1 bytes) | val1ORoffset1 (4 bytes) | type_in_db2 | val2ORoffset2 |....| data.........................
	// <------------------ param defs ----------------------------------------------------><-- String | byte[] values -->
	//
	// => number and date values are stored right after their type (on 4 bytes) in the "param defs" area
	// => byte[] and String values are stored after the "param defs" and referenced by an offset (on 4 bytes too) in the "param defs"

	static final int EP_PARAMS_DEF_SIZE = 5;

	String ep_static;
	int ep_params_count;
	int ep_params_real_size;

	public DBMS client;
	public static final String EP_START = "/*EP ";
	public static final String EP_STOP = " \u0000*/";
	public static final String META_START = " \u0000 ";
	public static final int META_START_LEN = META_START.length();

	static {
		Util.setTEPO( id_buffer );
		Util.setTEPO( id_buffer_genkey );
		Util.setTEPO( plan_params );
	}

	Statement( DBMS cl )
	{
		client = cl;
	}
	
	public java.sql.ResultSet executeQuery( int qepNo ) throws SQLException
	{
		this.ep_static = Integer.toString( qepNo );
		this.ep_params_real_size = 0;
		this.ep_params_count = 0;
		return query();
	}
	
	public int executeUpdate( int qepNo ) throws SQLException
	{
		this.ep_static = Integer.toString( qepNo );
		return update( Statement.NO_GENERATED_KEYS );
	}
	
	public int executeUpdate( int qepNo, int autoGeneratedKeys ) throws SQLException
	{
		this.ep_static = Integer.toString( qepNo );
		this.ep_params_real_size = 0;
		this.ep_params_count = 0;
		return update( autoGeneratedKeys );
	}

	@Override
	public java.sql.ResultSet executeQuery( String sql ) throws SQLException
	{
		throw new SQLException( "SQL Feature Not Supported" );
	}

	@Override
	public int executeUpdate( String sql ) throws SQLException
	{
		throw new SQLException( "SQL Feature Not Supported" );
	}

	@Override
	public int executeUpdate( String sql, int autoGeneratedKeys ) throws SQLException
	{
		throw new SQLException( "SQL Feature Not Supported" );
	}
	
	java.sql.ResultSet query() throws SQLException
	{
		this.client.call(
			Macro.CMD_QUERY,
			ep_static,
			((this.ep_params_real_size == 0) ? null : plan_params),
			this.ep_params_real_size,
			null );
		this.ep_params_real_size = this.ep_params_count * EP_PARAMS_DEF_SIZE;
		return this.res = new ResultSet( Integer.parseInt( this.ep_static ), this.client );
	}

	int update( int autoGeneratedKeys ) throws SQLException
	{
		byte[] ep_params = ( ep_params_real_size == 0 ) ? null : plan_params;
		int ret = 0;
		if ( autoGeneratedKeys == Statement.NO_GENERATED_KEYS )
		{
			this.client.call(
				Macro.CMD_UPDATE,
				ep_static,
				ep_params,
				ep_params_real_size,
				id_buffer );
			ret = DBMSFactory.bytea2int( id_buffer );
		}
		else if ( autoGeneratedKeys == Statement.RETURN_GENERATED_KEYS )
		{
			this.client.call(
				Macro.CMD_UPDATE_AND_GET_KEY,
				ep_static,
				ep_params,
				ep_params_real_size,
				id_buffer_genkey );
			ret = DBMSFactory.bytea2int( id_buffer_genkey, 4 );
			this.autoGeneratedIdGlobal = DBMSFactory.bytea2int( id_buffer_genkey );
		}
		else
			throw new SQLException( "Invalid autoGeneratedKeys: " + autoGeneratedKeys );
		this.ep_params_real_size = this.ep_params_count * EP_PARAMS_DEF_SIZE;
		return ret;
	}

	@Override
	public java.sql.ResultSet getGeneratedKeys() throws SQLException
	{
		this.res = new AutoGeneratedKeysResultSet( autoGeneratedIdGlobal );
		return this.res;
	}

	/**
	 * Proprietary method (not compliant with JDBC spec)
	 *
	 * @return
	 * @throws SQLException
	 */
	public int getGeneratedIdGlobalKeys() throws SQLException
	{
		return this.autoGeneratedIdGlobal;
	}

	@Override
	public void close() throws SQLException
	{
		if ( res != null )	// close result set
			res.close();
		client.unlockedReent();
	}
}