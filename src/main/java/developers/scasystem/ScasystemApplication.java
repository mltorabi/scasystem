package developers.scasystem;


import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import developers.scasystem.model.AppointmentRepository;
import developers.scasystem.model.BlogRepository;
import developers.scasystem.model.ImageRepository;
import developers.scasystem.model.MedicationRepository;
import developers.scasystem.model.PatientRepository;
import developers.scasystem.model.PrescriptionRepositry;
import developers.scasystem.model.Speciality;
import developers.scasystem.model.SpecialityRepository;
import developers.scasystem.model.Staff;
import developers.scasystem.model.StaffRepository;
import developers.scasystem.model.patient;

@SpringBootApplication
public class ScasystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScasystemApplication.class, args);
	}
	@Bean
	ApplicationRunner init(AppointmentRepository appRep, BlogRepository blogRep, ImageRepository imgRep, MedicationRepository medRep, PatientRepository pedRep, PrescriptionRepositry presRep, SpecialityRepository specialRep, StaffRepository staffRep) {
		return args->{
			patient[] Pateints = {
					new patient(123456, "Leo", "Xu", "1980-01-12", "778123456", "test@mail.com", "Vacnouver 123", "BC", "V5Z 1A2", "Vancouver", "test1234"),
					new patient(123456, "Summer", "Gumit","1988-05-12", "778925879", "test2@mail.com", "Vacnouver 345", "BC", "V5Z 1A2", "Vancouver", "test234"),
					new patient(123456, "Milad", "Torabi", "1988-08-12", "778123445", "test3@mail.com", "Vacnouver 987", "BC", "V5Z 1A2", "Vancouver", "test987654")
			};
			Staff[] Staffs = {
					new Staff("Ivan", "Wong", "1970-04-05", "998778554", "test@mail.com","test1234", 0),
					new Staff("Mani", "Amini", "2000-04-05", "998778554", "test12@mail.com","test1234", 1),
					new Staff("Sara", "Wong", "1990-04-05", "998778554", "test23@mail.com","test1234", 0),
			};
			Speciality[] specialites = {
					new Speciality("Cardiology", 5),
					new Speciality("General", 10)
			};
			specialites[0].AddStaff(Staffs[0]);
			specialites[1].AddStaff(Staffs[1]);
			for (int i = 0; i < Staffs.length; i++) {
				staffRep.save(Staffs[i]);
			}
			for (int i = 0; i < specialites.length; i++) {
				specialRep.save(specialites[i]);
			}
			for (int i = 0; i < Pateints.length; i++) {
				pedRep.save(Pateints[i]);
			}
			staffRep.findAll().forEach(System.out::println);
			pedRep.findAll().forEach(System.out::println);
		};
	}

}
