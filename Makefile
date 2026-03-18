node_modules:
	bun install

tailwind: node_modules
	bunx tailwindcss -i ./src/main.css -o ./resources/public/styles.css --watch

shadow: node_modules
	bunx shadow-cljs watch app

.PHONY: tailwind shadow
