package com.alibaba.nacos.example.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaojing
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosProviderApplication.class, args);
	}

	@RestController
	class EchoController {
		@RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
		public String echo(@PathVariable String string) {
			return "Hello CAE: " + string;
		}

		public void writeToDB() {
			Connection conn = null;
			String ip = System.getenv("RDS_ADDRESS");
			String port = System.getenv("RDS_PORT");
			String dbName =  System.getenv("RDS_DB_NAME");
		        String url = "jdbc:postgresql://ip:port/dbName?sslmode=verify-ca&sslrootcert=/home/Ruby/ca.pem";
		        String userName = System.getenv("RDS_USER_NAME");
		        String password = System.getenv("RDS_PASSWORD");

		        try {
			  Class.forName("org.postgresql.Driver");
			  conn = DriverManager.getConnection(url, userName, password);
			  System.out.println("Database connected");

			  Statement stmt = conn.createStatement();
			  ResultSet rs = stmt.executeQuery("INSERT * FROM mytable WHERE columnfoo = 500");
			  while (rs.next()) {
			    System.out.println(rs.getString(1));
			  }

			  rs.close();
			  stmt.close();
			  conn.close();
		        } catch (Exception e) {
			  e.printStackTrace();
			  System.out.println("Test failed");
		        }
		}
	}
}
