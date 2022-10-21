package backend;

public class FacultyService {
    private static FacultyService instance;

    private FacultyService()
    {}

    public static FacultyService getInstance()
    {
        if(instance==null)
            instance = new FacultyService();
        return instance;
    }
}
