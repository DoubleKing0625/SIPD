package org.inria.dmsp.test.TEST_returnUPDATE;

import java.io.InputStreamReader;
import java.io.PrintWriter;		// to produce the output of the test

import org.inria.database.QEPng;
import org.inria.dmsp.tools.DMSP_QEP_IDs;
import org.inria.dmsp.tools.DeltaLoader;
import org.inria.dmsp.tools.Tools_dmsp;

import test.jdbc.Tools;
import test.runner.ITest;

public class Test extends Tools implements ITest
{
	@Override
	public void run(PrintWriter out, String dbmsHost) throws Exception
	{
		// set output stream and instantiate the tools:		
		this.out = out;
		Tools_dmsp t = new Tools_dmsp(out);
		// this not a performance test ==> output is produced:
		perf = 0;

		// initialize the driver:
		init();
		// connect without authentication
		openConnection(dbmsHost, null);

		// load the DB schema
		String schema = "/org/inria/dmsp/schemaV4.rsc";
		byte[] schemaDesc = t.loadSchema(schema);
		int usedId = 404;
		Install_DBMS_MetaData(schemaDesc, usedId);
	    schemaDesc = null;
	    
		// load and install QEPs
		Class<?>[] executionPlans = new Class[] { org.inria.dmsp.EP_Synchro.class, org.inria.dmsp.schema.EP_TEST.class, 
				org.inria.dmsp.EP_UI.class, org.inria.dmsp.EP_PDS.class };
		QEPng.loadExecutionPlans( org.inria.dmsp.tools.DMSP_QEP_IDs.class, executionPlans );
		QEPng.installExecutionPlans( db );

	    // instantiate delta loader
	    DeltaLoader dl = new DeltaLoader(out, perf); 
		// open the delta file
	    InputStreamReader fr = new InputStreamReader(Test.class.getResourceAsStream("delta.csv"));
		// load the data into database
		dl.LoadDelta(fr, db); 

		if(perf==0){out.println("// ---------- TEST : UPDATE INFO ------------ ");}

		if(perf==0){out.println("// NON-EXISTING TUPLE IdG=1234567, only rows");}
		update(1234567);

		if(perf==0){out.println("// EXISTING TUPLE IdG=1126, only rows");}
		update(1126);

		if(perf==0){out.println("// NON-EXISTING TUPLE IdG=1234567, rows + keys");}
		update_freeze_sgbd(1234567);

		if(perf==0){out.println("// EXISTING TUPLE IdG=1126, rows + keys");}
		update_freeze_sgbd(1126);

		/*
		 * Commit and clean exit
		 */
		Save_DBMS_on_disk();
		Desinstall_DBMS_MetaData();
		Shutdown_DBMS();
	}

	// WARNING: this method freezes the SGBD!!! (because of RETURN_GENERATED_KEYS)
	private void update_freeze_sgbd(int idG) throws Exception
	{
		java.sql.PreparedStatement ps;
		int res, kres, ts_spt;

		ps = ((org.inria.jdbc.Connection)db).prepareStatement(DMSP_QEP_IDs.EP_UI.INFO_AC_UPDATE_BY_ID, java.sql.Statement.RETURN_GENERATED_KEYS);
		if(perf==0){out.println("-- TRY TO UPDATE INFO with idG="+idG);}
		ts_spt = ((org.inria.jdbc.Connection) db).getGlobalTimestamp();
		res = Tools_dmsp.Test_INFO_UPDATE(ts_spt, "NEW INFO1", 1010101, java.sql.Date.valueOf("1111-11-01"), 888, idG, ps);
		kres = getIdGlobalGetGeneratedKey(ps.getGeneratedKeys());
		if(perf==0){out.println("--> update return: row(s) updated = " + res + "; generated key = " + kres + ".");}
	}

	private void update(int idG) throws Exception
	{
		java.sql.PreparedStatement ps;
		int res, ts_spt;

		ps = ((org.inria.jdbc.Connection)db).prepareStatement(DMSP_QEP_IDs.EP_UI.INFO_AC_UPDATE_BY_ID);
		if(perf==0){out.println("-- TRY TO UPDATE INFO with idG="+idG);}
		ts_spt = ((org.inria.jdbc.Connection) db).getGlobalTimestamp();
		res = Tools_dmsp.Test_INFO_UPDATE(ts_spt, "NEW INFO1", 1010101, java.sql.Date.valueOf("1111-11-01"), 888, idG, ps);
		if(perf==0){out.println("--> update return: row(s) updated = " + res + ".");}
	}
}
