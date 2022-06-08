package app.util;

public class Routes {
	
	// MAIN CONTROLLER:	
	public final static String HOME = "/";
	public final static String TUTORIAL = "/readme";
	
	//BUILDING CONTROLLER 
	public final static String PRINCIPAL_BUILDING = "/building";
	public final static String LIST_BUILDING = "/buildings";
	public final static String BRING_BUILDING = "/{idBuilding}/classrooms";
	public final static String ADD_BUILDING = "/addBuilding";
	public final static String EDIT_BUILDING = "/edit/{idBuilding}";
	public final static String EDIT_POST_BUILDING = "/editBuilding";
	
	//CAREER CONTROLLER 
	public final static String PRINCIPAL_CAREER = "/career";
	public final static String LIST_CAREER = "/careers";
	public final static String BRING_CAREER = "/{idBuilding}/classrooms";
	public final static String ADD_CAREER = "/addCareer";
	public final static String EDIT_CAREER = "/edit/{idCareer}";
	public final static String EDIT_POST_CAREER = "/editCareer";
	
	//CLASSROOM CONTROLLER 
	public final static String PRINCIPAL_CLASSROOM = "/classroom";
	public final static String LIST_CLASSROOM = "/listBuilding";
	public final static String BRING_CLASSROOM = "/classroom/{idClassroom}";
	public final static String ADD_LABORATORY= "/addLaboratory/{idBuilding}";
	public final static String ADD_TRADITIONAL= "/addTraditional/{idBuilding}";
	public final static String EDIT_CLASSROOM = "edit/{idClassroom}";
	public final static String EDIT_LABORATORY= "/editLaboratory/{idBuilding}";
	public final static String EDIT_TRADITIONAL= "/editTraditional/{idBuilding}";
	
	//DEPARTMENT CONTROLLER 
	public final static String PRINCIPAL_DEPARTMENT = "/department";
	public final static String LIST_DEPARTMENT = "/departments";
	public final static String BRING_DEPARTMENT = "/{idBuilding}/classrooms";
	public final static String ADD_DEPARTMENT = "/addDepartment";
	public final static String EDIT_DEPARTMENT = "/edit/{idDepartment}";
	public final static String EDIT_POST_DEPARTMENT = "/editDepartment";
	
	//MATTER CONTROLLER 
	public final static String PRINCIPAL_MATTER = "/matter";
	public final static String LIST_MATTER = "/matters";
	public final static String BRING_MATTER = "/{idBuilding}/classrooms";
	public final static String ADD_MATTER = "/addMatter";
	public final static String EDIT_MATTER = "/edit/{idMatter}";
	public final static String EDIT_POST_MATTER = "/editMatter";
	
	//ORDER_NOTE CONTROLLER 
	public final static String PRINCIPAL_ORDER_NOTE = "/orderNote";
	public final static String LIST_ORDER_NOTE = "/orderNotes";
	public final static String EDIT_ORDER_NOTE = "/edit/{idOrderNote}";
	public final static String BRING_ORDER_NOTE = "/orderNote/{idOrderNote}";
	public final static String ADD_DAY= "/addDayOrder";
	public final static String EDIT_DAY= "/editDayOrder";
	public final static String ADD_QUARTER= "/addQuarterOrder";
	public final static String EDIT_QUARTER= "/editQuarterOrder";
	
	//SPACE CONTROLLER 
	public final static String PRINCIPAL_SPACE = "/space";
	public final static String ADD_SPACE_QUARTER = "/addSpace/quarter";
	public final static String LIST_SPACE = "/spaces";
	public final static String BRING_SPACE_SEARCH = "spaces/search";
	public final static String ADD_SPACE = "/addSpace/{buildingName}-{idBuilding}";
	public final static String EDIT_SPACE = "/edit/{idSpace}";
	public final static String EDIT_POST_SPACE = "/editSpace";
	
	//TEACHER CONTROLLER 
	public final static String PRINCIPAL_TEACHER = "/teacher";
	public final static String LIST_TEACHER = "/teachers";
	public final static String ADD_TEACHER = "/addTeacher";
	public final static String EDIT_TEACHER = "/edit/{idTeacher}";
	public final static String EDIT_POST_TEACHER = "/editTeacher";
	
	//USER CONTROLLER 
	public final static String PRINCIPAL_USER = "/user";
	public final static String LIST_USER = "/users";
	public final static String ADD_USER = "/addUser";
	public final static String BRING_USER_USERNAME = "/user/{userName}";
	public final static String EDIT_USER = "/edit/{idUser}";
	public final static String EDIT_POST_USER = "/editUser";
	
	//USER_ROLE CONTROLLER 
	public final static String PRINCIPAL_USER_ROLE = "/role";
	public final static String LIST_USER_ROLE = "/roles";
	public final static String ADD_USER_ROLE = "/addRole";
	public final static String BRING_USER_ROLE = "/role/{idRole}";
	public final static String EDIT_USER_ROLE = "/edit/{idRole}";
	public final static String EDIT_POST_USER_ROLE = "/editRole";
	
	//OTHERS
	public final static String DELETE = "/delete";
	public final static String MODEL_LIST_MATTER = "listMatter";
	public final static String MODEL_LIST_TEACHER = "listTeacher";
	public final static String MODEL_LIST_CLASSROOM = "listClassroom";

}
