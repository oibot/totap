{
  outputs = { self, nixpkgs }:
    let
      allSystems = [
        "x86_64-linux"
        "aarch64-linux"
        "x86_64-darwin"
        "aarch64-darwin"
      ];

      forAllSystems = f: nixpkgs.lib.genAttrs allSystems (system: f {
        pkgs = import nixpkgs { inherit system; };
      });
    in
    {
      devShells = forAllSystems ({ pkgs }: {
        default = pkgs.mkShell {
          packages = with pkgs; [
            babashka
            clj-kondo
            clojure
            clojure-lsp
            git
            jdk21
            temurin-jre-bin
          ];

          JAVA_HOME = "${pkgs.jdk21}/lib/openjdk";

          shellHook = ''
            export JAVA_TOOL_OPTIONS="-Djdk.attach.allowAttachSelf=true $JAVA_TOOL_OPTIONS"
            echo "Clojure dev shell ready. Java: $(java -version 2>&1 | head -n1)"
          '';
        };
      });
    };
}
