package hello

import (
	"testing"
	"os"
	"io/ioutil"
	"strings"
)

func TestPrintHello(t *testing.T){
	rescueStdout := os.Stdout
	r, w, _ := os.Pipe()
	os.Stdout = w
	printHelloWorld()
	w.Close()
	out, _ := ioutil.ReadAll(r)
	os.Stdout = rescueStdout
	if strings.EqualFold(string(out), "hello world") {
		t.Fatal("expected hello world, got", string(out))
	}
}
