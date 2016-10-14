var debug = process.env.NODE_ENV !== "production";
var webpack = require('webpack');
var _ = require('lodash');
var autoprefixer = require('autoprefixer');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var path = require('path');
var srcDir = './src/';
var targetDir = '../../../target/classes/static';

var webpackConfig = {};

webpackConfig.devtool = debug ? "inline-sourcemap" : null;

webpackConfig.entry = {
  'vendor': srcDir + "/vendor.js",
  'app': srcDir + "/app.js"
};

webpackConfig.output = {
  path: targetDir,
  filename: "js/[name].js"
};
webpackConfig.resolve = {
  cache: false,
  root: path.join(__dirname, srcDir),
  // only discover files that have those extensions
  extensions: ['', '.js', '.jsx', '.css', '.less', '.html']
};

webpackConfig.module = {};


webpackConfig.module.loaders = [
  {
    test: /\.css$/,
    loader: ExtractTextPlugin.extract('style', 'css?sourceMap!postcss')
  },
  {
    test: /\.less$/,
    loader: ExtractTextPlugin.extract('style', 'css!less')
  },
  {
    test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
    loader: 'url?limit=10000&mimetype=application/font-woff!file?name=fonts/[name].[ext]'
  },
  {
    test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
    loader: 'file?name=/fonts/[name].[ext]'
  },
  {
    test: /\.jsx?$/,
    exclude: /node_modules/,
    loader: 'babel-loader',
    query: {
      presets: ['react', 'es2015', 'stage-0'],
      plugins: ['react-html-attrs', 'transform-decorators-legacy', 'transform-class-properties']
    }
  }
];

var plugins = [
  new HtmlWebpackPlugin({
    template: './src/index.html',
    inject: 'body'
  }),
  new ExtractTextPlugin('css/main.css')
];

var prodPlugins = [
  new webpack.optimize.DedupePlugin(),
  new webpack.optimize.OccurenceOrderPlugin(),
  new webpack.optimize.UglifyJsPlugin({mangle: false, sourcemap: false})
];

webpackConfig.plugins = debug ? plugins : _.extend(plugins, prodPlugins);

webpackConfig.postcss = [
  autoprefixer({
    browsers: ['last 2 version']
  })
];

webpackConfig.eslint = {
  configFile: './.eslintrc',
  failOnError: true
};

module.exports = webpackConfig;

