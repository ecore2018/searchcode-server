#!/bin/bash
echo "Launching searchcode server..."
exec java -jar searchcode-1.3.11.jar "$@"
