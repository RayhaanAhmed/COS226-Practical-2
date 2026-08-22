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
        flag[threadId].value = true;

        int max = 0;

        for(int i = 0; i < n; i++){
           if(label[i].value > max){
            max = label[i].value;
           }
        }

        label[threadId].value = max + 1;

        for(int k = 0;k < n; k++){
            
            if(k == threadId){
                continue;
            }
            while(flag[k].value && 
                 (label[k].value < label[threadId].value ||
                 (label[k].value == label[threadId].value && 
                  k < threadId))){
            
            }
        }

    }

    @Override
    public void unlock(int threadId) 
    {
        flag[threadId].value = false;
    }
}