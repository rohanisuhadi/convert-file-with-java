import java.io.File;

class MyTools {

  public static void main(String args[]){

    MyTools myTool = new MyTools();
    String pathFileNew = null;
    String typeFile = "text";
    for (int i = 0; i < args.length; i++) {
      // System.out.println(args[i]);
      switch (args[i]) {
        case "-t": 
          try{
            typeFile = args[i+1];
          }
          catch(Exception e){
            System.out.println("Pleas input text or json after -t");
            myTool.printHelp();
            System.exit(0);
          }
          break;
        case "-o":
          try{ 
            pathFileNew = args[i+1];
          }
          catch(Exception e){
            System.out.println("Pleas input path file destination after -o");
            myTool.printHelp();
            System.exit(0);
          }
          break;
        case "-h":
          myTool.printHelp();
          break;
      }
    }

    File file = null;
    try{
      file = new File(args[0]);
    }
    catch(Exception e){
      System.out.println("Pleas input file path");
      myTool.printHelp();
      System.exit(0);
    }

    /* check file */
    if (  !file.exists()){
      System.out.println("file not found");
      myTool.printHelp();
      System.exit(0);
    }

    /* params */
    
    
    String pathFileOld = file.getPath();
    

    /* switch type file to extension */
    String extension = "txt";
    switch (typeFile) {
        case "text": 
          extension = ".txt";
          break;
        case "json": 
          extension = ".json";
          break;
    }
    
    /* convert file */

    /* check param destination file */
    if( pathFileNew == null ){
      pathFileNew = pathFileOld.replace(myTool.getExtension(file.getName()), extension );
    }
    
    // System.out.println( pathFileNew );
    File fileConvert = new File(pathFileNew);
    boolean success = file.renameTo(fileConvert);
    if (success) {
      System.out.println("File success convert");
    }
    else{
      System.out.println("File failed convert");
    }
  }

  public String getExtension(String fileName)
  {
    String extension = "";
    int i = fileName.lastIndexOf('.');
    if (i > 0) {
      extension = fileName.substring(i+1);
    }
    return "."+extension;
  }

  public void printHelp()
  {
    System.out.println("");
    System.out.println("Help:");
    System.out.println("1. Convert file to text (txt)");
    System.out.println("MyTools path file");
    System.out.println("");
    System.out.println("Option :");
    System.out.println("-t select type file convert (json or txt)");
    System.out.println("-o set path file convert");
    System.out.println("");
    System.out.println("Example:");
    System.out.println("MyTools /var/log/nginx/error.log -t json -o /User/johnmayer/Desktop/nginxlog.json");
    System.exit(0);
  }

}