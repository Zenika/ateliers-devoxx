package webserver

import (
	"testing"
	"net/http"
	"strings"
	"io/ioutil"
)

func TestWebserver(t *testing.T) {
	go webserver()
	res, err := http.Get("http://127.0.0.1:9000/hello/devoxx")

	if err != nil {
		t.Fatal("got an error when calling http server", err)
	}

	defer res.Body.Close()
	body, err := ioutil.ReadAll(res.Body)
	if err != nil {
		t.Fatal("got an error when reading body", err)
	}

	if !strings.EqualFold(string(body), "Hello there devoxx!") {
		t.Fatal("expected hello there devoxx!, got ", string(body))
	}

	res, err = http.Get("http://127.0.0.1:9000/hello/zenika")

	if err != nil {
		t.Fatal("got an error when calling http server", err)
	}

	defer res.Body.Close()
	body, err = ioutil.ReadAll(res.Body)

	if err != nil {
		t.Fatal("got an error when reading body", err)
	}

	if !strings.EqualFold(string(body), "Hello there zenika!") {
		t.Fatal("expected hello there zenika!, got ", string(body))
	}
}
