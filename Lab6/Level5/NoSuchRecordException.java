class NoSuchRecordException extends Exception {
    public NoSuchRecordException(String id, String code, String name) {
        super("No such record: " + id + " " + code + " " + name);
    }
}
