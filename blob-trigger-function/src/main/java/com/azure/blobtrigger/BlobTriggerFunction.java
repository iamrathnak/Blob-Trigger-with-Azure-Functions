package com.azure.blobtrigger;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Azure Blob trigger.
 */
public class BlobTriggerFunction {
    /**
     * This function will be invoked when a new or updated blob is detected at the specified path. The blob contents are provided as input to this function.
     */
    @FunctionName("BlobTrigger-Java")
    //@StorageAccount("AzureStorageDemoConnectionStringSrc")
    public void run(
        @BlobTrigger(name = "content", path = "testazureblob/{name}", dataType = "binary",connection = "AzureStorageDemoConnectionStringDest") byte[] content,
        @BindingName("name") String name,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Blob trigger function processed a blob. Name: " + name + "\n  Size: " + content.length + " Bytes");
        String[] values= new String(content).split("\n");
        double sum=0;
        double min = Double.MAX_VALUE;
        double max= Double.MIN_VALUE;
        double avg=0;
        for(String val:values){
            double realVal=Double.valueOf(val);
            min=Math.min(realVal,min);
            max=Math.max(realVal,max);
            sum+=realVal;


        }
        avg=sum/values.length;
        context.getLogger().info("Minimum: "+min+" Maximum: "+max+" Average: "+avg);

    }
}
