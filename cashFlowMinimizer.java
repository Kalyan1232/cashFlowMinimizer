import java.util.*;
class Bank{
    public String name;
    public int netAmount;
    public Set<String> types;
}
class Pair<K,V>{
    private K key;
    private V value;
    public Pair(K key,V value){
        this.key=key;
        this.value=value;
    }
    public K getKey(){
        return key;
    }
    public void setKey(K key){
        this.key=key;
    }
    public V getValue(){
        return value;
    }
    public void setValue(V value){
        this.value=value;
    }
}
public class cashFlowMinimizer{
    public static void main(String args[]){
      System.out.println("\t\t************ Welcome to Cash Flow Minimizer System ************\n\n");
      System.out.println("This system minimizes the number of transactions among multiple banks in the different corners of the world that use different modes of payment. There is one world bank (with all payment modes) to act as an intermediary between banks that have no common mode of payment.\n")
      System.out.println("Enter the number of banks for the transactions");
      
      Scanner sc = new Scanner(System.in);
      int numBanks = sc.nextInt();
      Bank[] banks =new Bank[numBanks];

      Map<String,Integer> map=new HashMap<>();
      System.out.println("Enter the details of the banks and transactions as stated:");
      System.out.println("Bank name, number of payment modes it has, and the payment modes.");
      System.out.println("Bank name and payment modes should not contain spaces.");
        
      int maxNumtypes =0;

      for(int i=0;i<numBanks;i++){
        if(i==0){
            System.out.println("World Bank");
        }
        else{
            System.out.println(" Bank "+i+": ");
        }
        String name=sc.next();
        banks[i]=new Bank();
        banks[i].name=name;
        map.put(name,i);

        int numtypes=sc.nextInt();
        
        // It is One of the Edge Test Case
        if (i == 0){ maxNumtypes = numtypes;}

        Set<String> types =new HashSet<>(numtypes);

        for(int j=0;j<numtypes;j++){
            types.add(sc.next());
        }
        banks[i].types=types;
      }
      System.out.println("Enter the number of transactions");
      int numTransactions=sc.nextInt();
      int[][] graph = new int[numBanks][numBanks];

      System.out.println("Enter the details of the transactions as stated:");
      System.out.println("Bank name from which the transaction originates, bank name to which the transaction is made, and the amount.");
      
      for(int i=0; i<numTransactions;i++){
        String from = sc.next();
        String to = sc.next();
        int amt=sc.nextInt();
        Integer fromIndex=map.get(from);
        Integer toIndex=map.get(to);

        if(fromIndex!=null && toIndex!=null){
            int fromIdx = fromIndex.intValue();
            int toIdx = toIndex.intValue();
            graph[fromIdx][toIdx]=amt;
        }else{
               System.out.println("Invalid Bank Name "+from+" , "+to);
        }
      }
      minimizeCashFlow(numBanks,banks,map,numTransactions,graph,maxNumtypes);
      sc.close();
    }
}