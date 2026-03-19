node_modules:
	bun install

tailwind: node_modules
	bunx tailwindcss -i ./src/main.css -o ./resources/public/styles.css --watch

shadow: node_modules
	bunx shadow-cljs watch app

build-css: node_modules
	bunx tailwindcss -i ./src/main.css -o ./resources/public/styles.css --minify

build-js: node_modules
	bunx shadow-cljs release app

build: build-css build-js

.PHONY: tailwind shadow build-css build-js build
