public class SporterDAO {

    public List<SporterModel> findAll() {
        // open connection, run SELECT, map each row to SporterModel, return list
    }

    public SporterModel findById(int id) {
        // SELECT ... WHERE id = ?
    }

    public void insert(SporterModel sporter) {
        // INSERT INTO sporters ...
    }

    public void update(SporterModel sporter) {
        // UPDATE sporters SET ... WHERE id = ?
    }

    public void delete(int id) {
        // DELETE FROM sporters WHERE id = ?
    }
}