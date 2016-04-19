package hello

import (
	"testing"
	"os"
	"io/ioutil"
	"strings"
)

func TestPrintHello(t *testing.T){
	osStdout := os.Stdout
	r, w, _ := os.Pipe()
	os.Stdout = w
	PrintHelloWorld()
	w.Close()
	out, _ := ioutil.ReadAll(r)
	os.Stdout = osStdout
	outputString := strings.TrimSpace(string(out))
	if !strings.EqualFold(outputString, "hello world") {
		t.Fatal("expected hello world, got", string(out))
	}
}
