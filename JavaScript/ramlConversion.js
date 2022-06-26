const converter = require('oas-raml-converter')
const fs = require('fs');

async function convert(fileName, outputFile) {
    const raml10ToOas20 = new converter.Converter(converter.Formats.RAML, converter.Formats.OAS20)
    raml10ToOas20.convertFile(fileName)
        .then(function (oas) {
            fs.writeFile(outputFile, oas, err => {
                if (err) {
                    console.error(err);
                }
            })
        })
        .catch(function (err) {
            console.error(err)
        });
}

convert("INPUT_PATH", "OUTPUT_PATH")