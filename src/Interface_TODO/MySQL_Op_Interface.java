public interface MySQL_Op_Interface {
    //根据id从数据库中获取密码，加密传输
    String getPWByID(String id);
    //根据标识从数据库中获取密码
    String getEmailByID(String id);
    //根据账号改变其原密码
    void changePWByID(String id, String newpw);
    //插入新用户
    void insertUser(String id ,String pw ,String email);
}
