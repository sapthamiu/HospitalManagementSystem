package alldepartments;
import common.Doctor;
import common.LL;

abstract class BaseDepartment implements Departments{
	public LL<Doctor> doctorList = new LL<Doctor>();
	private int doctorCount;

	public BaseDepartment(){
		doctorCount=0;
	}

	public abstract String getDepartmentName();

	public int getDoctorCount(){
		return doctorCount;
	}
	
	public void InsertDoctor(Doctor doctor){
		if(doctor.getDept().equals(getDepartmentName())){
			doctorList.insert(doctor);
			doctorCount++;
		}
	}

	public void RemoveDoctor(Doctor doctor){
		if(doctor.isRetired()){
			doctorList.delete(doctor);
			doctorCount--;
			System.out.println("Doctor "+doctor.getDname()+" has retired and no longer is in the "+getDepartmentName()+" department");

		}
	}

	public void DisplayDoctors(){
		doctorList.display();
	}
	//just in case doctor count might be required somewhere
}
