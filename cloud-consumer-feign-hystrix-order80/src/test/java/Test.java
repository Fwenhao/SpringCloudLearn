/**
 * @author fwh
 * @version 1.0
 * @date 2021/2/3 9:40
 * @description
 */
public class Test
{
    public static int aMethod(int i)throws Exception
    {
        try{
            return 10 / i;
        }
        catch (Exception ex)
        {
            throw new Exception("exception in a Method ");
        } finally{
            System.out.printf("finally ");
        }
    }

    public static void main(String [] args)
    {
        try
        {
            aMethod(0);
        }
        catch (Exception ex)
        {
            System.out.printf("exception in main");
        }
        System.out.printf(" finished");
    }
}




