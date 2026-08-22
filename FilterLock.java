public class FilterLock implements Lock 
{

    private final int n;
    private final VolatileInt[] level;
    private final VolatileInt[] victim;

    //same as peterson but work with n threads and not just 2 anymore. Thread must traverse n-1 waiting rooms to get lock
    public FilterLock(int n) 
    {
        this.n = n;
        level = new VolatileInt[n];
        victim = new VolatileInt[n];

        for (int i=0 ; i<n ; i++){
            level[i] = new VolatileInt(0);
            victim[i] = new VolatileInt(0);
        }

    }

    @Override
    public void lock(int threadId) 
    {
        for(int i = 0; i < n; i++){

            level[threadId].value = i;

            victim[i].value = threadId;

            boolean conflict;

            do{
                conflict = false;
                
                for (int k = 0; k < n; k++){

                    if(k != threadId && level[k].value >= i){

                        conflict = true;
                        break;
                    }
                }
            }
            while(conflict && victim[i].value == threadId);

            
        }
    }

    @Override
    public void unlock(int threadId) 
    {
        
    }
}