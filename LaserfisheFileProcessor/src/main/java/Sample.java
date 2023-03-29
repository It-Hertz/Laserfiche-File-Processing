/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author 
 */
public class Sample {

    public static void main(String[] args) {
        String servicePrincipalKey = "PJ_pb-BeAiGvs7YeqtWY";
        String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiYzg1MmQyMGQtYzQyMS00ZTMwLWJmNWQtZDQ2ZjM2YzM0ZTNkIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogIklwdWZKbFU1WVNLdXdTbjRmcUhGR05CRGI2V1VJNkpVaW9nSk5hbXBWSTAiLAoJCSJ4IjogImVRUnpGUHRCUzhOR0dYTUJyNUdMdTdnRHBTaE10UFFXcDZaSU4wUWVCaU0iLAoJCSJ5IjogImdtNEp4STdvUktBX1V5bTN1U3NUQTFTaThyWWQyZTRXZTVMcTQyQ2lyVU0iLAoJCSJkIjogInZVcUdQM1l0SmlNVEcwMEJ6R25TWThaeUFCblB6WFZGdmNfTnppX01mdkEiLAoJCSJpYXQiOiAxNjc3Mjk3NDE5Cgl9Cn0=";
		String repositoryId = "r-0001d410ba56";
        AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);

        RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                servicePrincipalKey, accessKey);

        // Get information about the ROOT entry, i.e. entry with ID=1
        int rootEntryId = 1;
        Entry entry = client.getEntriesClient()
                .getEntry(repositoryId, rootEntryId, null).join();

        System.out.println(
                String.format("Entry ID: %d, Name: %s, EntryType: %s, FullPath: %s",
                        entry.getId(), entry.getName(), entry.getEntryType(), entry.getFullPath()));

        // Get information about the child entries of the Root entry
        ODataValueContextOfIListOfEntry result = client
                .getEntriesClient()
                .getEntryListing(repositoryId, rootEntryId, true, null, null, null, null, null, "name", null, null, null).join();
        List<Entry> entries = result.getValue();
        for (Entry childEntry : entries) {
            System.out.println(
                    String.format("Child Entry ID: %d, Name: %s, EntryType: %s, FullPath: %s",
                            childEntry.getId(), childEntry.getName(), childEntry.getEntryType(), childEntry.getFullPath()));
        }

        // Download an antry 
        int entryIdToDownload = 25;
        final String FILE_NAME = "DownloadedFile.txt";
        Consumer<InputStream> consumer = inputStream -> {
            File exportedFile = new File(FILE_NAME);
            try (FileOutputStream outputStream = new FileOutputStream(exportedFile)) {
                byte[] buffer = new byte[1024];
                while (true) {
                    int length = inputStream.read(buffer);
                    if (length == -1) {
                        break;
                    }
                    outputStream.write(buffer, 0, length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        client.getEntriesClient()
                .exportDocument(repositoryId, entryIdToDownload, null, consumer)
                .join();

        client.close();
    }
}
