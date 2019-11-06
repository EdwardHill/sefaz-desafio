package br.com.sefaz.desafio.util;

import org.hsqldb.util.DatabaseManagerSwing;

/* 	Chaveamento para inicar o Database HSQLDB local(arquivo) ou servidor(server), 
 * para conferencia da persistência das entidades.
 *	É necessário parar terminar qualquer processo java para iniciar com êxito.*/



	public class StartHSQLDB {
		static String FILE_URL="file:C:/banco/desafios";
		
		
		public static void main(String[] args) {
			local();		
			//server();
		}
		static void local() {
			final String[] dbArgs = { "--user", "sa", "--password", "", "--url", "jdbc:hsqldb:" +FILE_URL };
			DatabaseManagerSwing.main(dbArgs);
			
		}
		static void server() {
			final String[] dbArg = {"--database.0", FILE_URL, "--dbname.0", "desafios","--port","5454"};
			org.hsqldb.server.Server.main(dbArg);
			//final String[] dbArgsServer = { "--url", "jdbc:hsqldb:hsql://localhost:5454/desafios" };
			//DatabaseManagerSwing.main(dbArgsServer);
		}	
	}
	
