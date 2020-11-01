package db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenerateTable {

	public static void main(String[] args) {
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(
				"NewPersistenceUnit"
			);
			factory.close();
			System.out.println("Done!");
		} catch (Exception e) {
			System.out.println(GenerateTable.class.getSimpleName());
			System.out.println(e.getMessage());
		}
	}
}
