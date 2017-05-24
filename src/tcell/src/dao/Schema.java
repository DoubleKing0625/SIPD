package dao;

/**
 * 
 * @author Majdi Ben Fredj
 * 
 * This class contains the schema database
 *
 */
public class Schema {
	public static final String META =
			"TAB_DESC,9\n"+
					"0	QEP	512\n"+
					"1	LogDeleted	12\n"+
					"2	UpdateLog	512\n"+
					"3	User	49\n"+
					"4	File	456\n"+
					"5	MyInfo	73\n"+
					"6	Tag	106\n"+
					"7	Association1	12\n"+
					"8	Association2	12\n"+
					"COL_DESC,35\n"+
					"0	0	IdGlobal	4	1	0\n"+
					"1	0	QEPStr	460	0	4\n"+
					"2	0	SQLStr	24	9	464\n"+
					"3	0	ExplainStr	24	9	488\n"+
					"4	1	TabId	4	1	0\n"+
					"5	1	TuplePos	4	1	4\n"+
					"6	1	Flag	4	1	8\n"+
					"7	2	TabId	4	1	0\n"+
					"8	2	TuplePos	4	1	4\n"+
					"9	2	TupleSize	4	1	8\n"+
					"10	2	CompleteTup	500	0	12\n"+
					"11	3	IdGlobal	4	1	0\n"+
					"12	3	TCELLIP	17	0	4\n"+
					"13	3	TCELLPORT	4	1	21\n"+
					"14	3	PUBLICKEY	24	9	25\n"+
					"15	4	IdGlobal	4	1	0\n"+
					"16	4	FILEGID	102	0	4\n"+
					"17	4	FILEID	102	0	106\n"+
					"18	4	SECRETKEY	102	0	208\n"+
					"19	4	IV	32	0	310\n"+
					"20	4	TYPE	12	0	342\n"+
					"21	4	DESCRIPTION	102	0	354\n"+
					"22	5	IdGlobal	4	1	0\n"+
					"23	5	MYTCELLIP	17	0	4\n"+
					"24	5	MYTCELLPORT	4	1	21\n"+
					"25	5	MYPUBLICKEY	24	9	25\n"+
					"26	5	MYPRIVATEKEY	24	9	49\n"+
					"27	6	IdGlobal	4	1	0\n"+
					"28	6	TagName	102	0	4\n"+
					"29	7	IdGlobal	4	1	0\n"+
					"30	7	IdFile	4	1	4\n"+
					"31	7	IdTag	4	1	8\n"+
					"32	8	IdGlobal	4	1	0\n"+
					"33	8	IdUser	4	1	4\n"+
					"34	8	IdTag	4	1	8\n"+
					"FK_DESC,4\n"+
					"7	30	4	15\n"+
					"7	31	6	27\n"+
					"8	33	3	11\n"+
					"8	34	6	27\n"+
					"SKT_DESC,2\n"+
					"0	7	Association1	8\n"+
					"1	8	Association2	8\n"+
					"SKT_COL_DESC,4\n"+
					"0	0	4	15	1\n"+
					"0	4	6	27	1\n"+
					"1	0	3	11	1\n"+
					"1	4	6	27	1\n"+
					"CI_DESC,7\n"+
					"0	0	0	0	1\n"+
					"1	3	3	11	1\n"+
					"2	4	4	15	1\n"+
					"3	5	5	22	1\n"+
					"4	6	6	27	1\n"+
					"5	7	7	29	1\n"+
					"6	8	8	32	1\n"+
					"";
}