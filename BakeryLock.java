public class BakeryLock implements Lock 
{

    private final int n;
    private final VolatileBoolean[] flag;
    private final VolatileInt[] label;

    //first come first serve and uses boolean. "wait until no thread with an earlier number is trying to acquire it"
    public BakeryLock(int n) 
    {
        this.n = n;
        flag = new VolatileBoolean[n]; //boolean used for flag
        label = new VolatileInt[n];

        for (int i=0 ; i<n ; i++){
            flag[i] = new VolatileBoolean(false);//default to false
            label[i] = new VolatileInt(0);
        }
    }

    @Override
    public void lock(int threadId) 
    {
        
    }

    @Override
    public void unlock(int threadId) 
    {
        
    }
}