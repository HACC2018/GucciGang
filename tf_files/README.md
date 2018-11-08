Copy the optimized_graph.lite and retrained_labels.txt to the assets directory in the android app directory to replace the model with the new one.

Example:
```
cp optimized_graph.lite ../android/tflite/app/src/main/assets/graph.lite
cp retrained_labels.txt ../android/tflite/app/src/main/assets/labels.txt
```
