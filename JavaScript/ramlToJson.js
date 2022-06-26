/**
 * Example of parsing and generating RAML 0.8 files.
 */
 const wap = require('webapi-parser').WebApiParser
 const path = require('path')
 const fs = require('fs');
 
 async function main () {
   // Parse RAML 0.8 file
   const inPath = path.join(__dirname, 'INPUT_PATH')
   const model = await wap.raml10.parse(`file://${inPath}`)
   const resolved = await wap.raml10.resolve(model)

   const generated = await wap.oas20.generateString(resolved)
   fs.writeFile('OUTPUT_PATH', generated, err => {
    if (err) {
        console.error(err);
    }
})
     // Generate file with changed OAS 3.0 content
     console.log(generated)
   }
   
   main()