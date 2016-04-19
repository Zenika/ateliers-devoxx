package webserver

import (
	"fmt"
	"net/http"
)

func handler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Hello there %s!", r.URL.Path[7:])
}

func webserver() {
	http.HandleFunc("/hello/", handler)
	http.ListenAndServe(":9000", nil)
}
