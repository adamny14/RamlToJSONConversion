package ramlConversion;

import webapi.Oas20;
import webapi.Raml10;
import webapi.WebApiBaseUnit;
import webapi.WebApiDocument;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

public class ramlConversion {
	// **************************************************************************
	// * Name: translateRAMLToOAS *
	// * Params: fileName - path to the raml we need to convert *
	// * Returns: oas - string with the converted Open API Spec JSON *
	// **************************************************************************
	public static String transalteRAMLToOAS(String path)
			throws InterruptedException, ExecutionException, FileNotFoundException {
		// Path needs to have the file:// prefix in it, otherwise it cant load the file
		String filePath = "file://" + path;
		// We need to parse the RAML to allow for the conversion
		WebApiBaseUnit doc = Raml10.parse(filePath).get();
		// We need to resolve inputs otherwise it will have links to examples and data
		// types.
		WebApiDocument resolved = (WebApiDocument) Raml10.resolve(doc).get();
		// We need to convert it to Open API spec 2.0 and generate a JSON string to be
		// returned.
		String oas = Oas20.generateString(resolved).get();
		return oas;

	}

	public static void main(String[] args) {
		try {
			System.out.println(transalteRAMLToOAS("PATH_TO_RAML"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
