module.exports = {
  root: true,
  parserOptions: {
    parser: "babel-eslint",
  },
  env: {
    browser: true,
  },
  extends: ["plugin:vue/recommended", "prettier"],
  plugins: ["vue", "prettier"],
  rules: {
    "nenerator-start-spacing": "off",
    "no-unsafe-finally": 0,
    "no-return-assign": 0,
    "comma-dangle": ["error", "always-multiline"],
    "prettier/prettier": ["error", { endOfLine: "auto" }],
    quotes: "off",
    "no-extra-semi": "off",
  },
};
