package lookUp;

public abstract class LookUp {
    public void ask(){
        this.getBean().ask();
    }

    public abstract User getBean();
}
