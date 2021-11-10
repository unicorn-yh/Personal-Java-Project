package cn.edu.bit.ch07.dao;

public abstract class StudentDatas {
    private static String datas;
    private static DataSource dataSource;
    public static enum DataSource {
        STRING, FILE, DATABASE  // 学生数据分别保存在字符串，文件，数据库中
    }
    
    static {
        setDataSource(DataSource.STRING); // 默认是字符串
    }

    public static String from(DataSource dataSource) throws Exception {
        String result = "";
        switch (dataSource) {
            case STRING:
                result = new StudentDatasFromString().getStudents();
                break;
            case FILE:
                result = new StudentDatasFromFile().getStudents();
                break;
            case DATABASE:
                break;
        }
        return result;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        StudentDatas.dataSource = dataSource;
    }

    public static String getDatas() {
        return datas;
    }

    public static void setDatas(String datas) {
        StudentDatas.datas = datas;
    }

    public abstract String getStudents() throws Exception;
}
