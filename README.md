# Sox.quark

## A simple SuperCollider interface for SoX

A simple interface for SoX in SuperCollider, making offline processing of sounds via SoX a bit simpler and easier to integrate into a SuperCollider workflow.

## Installation

Open up SuperCollider and evaluate the following line of code:
`Quarks.install("https://github.com/madskjeldgaard/sox.quark")`

## Usage

All commands are class methods of `Sox` and they have sensible defaults, if you do not input parameter values

```supercollider
// Normalize
Sox.normalize("/Users/mads/tapedegrad1.wav", "/Users/mads/tapedegrad1_normalized.wav", (-0.1))

// Split by silence (using default values)
Sox.splitBySilence("/Users/mads/reallylongfile.wav", "/Users/mads/partOfReallyLongFile.wav")

// Run arbitrary sox command
Sox.run("inFile.wav", "outFile.wav", "gain", "-3", "pad", "0", "3", "reverb");
```

An example of batch-processing a folder of files:

```supercollider
(
var folder = "~/tmp/sounds";
var folderPath = PathName(folder);

folderPath.filesDo{|file|
    var inFile = file;
    var outFile = inFile.pathOnly +/+ PathName(inFile.fileNameWithoutExtension ++ "_normalized" ++ "." ++ inFile.extension);
    Sox.normalize(inFile.fullPath, outFile.fullPath);
};

)
```
