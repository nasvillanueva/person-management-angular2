var webpackMerge = require('webpack-merge');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var commonConfig = require('./webpack.common.js');
var helpers = require('./helpers');
var targetDir = '../../../target/classes/static';

module.exports = webpackMerge(commonConfig, {
  devtool: 'cheap-module-eval-source-map',

  output: {
    path: targetDir,
    filename: 'js/[name].js',
    chunkFilename: 'js/[id].chunk.js'
  },

  plugins: [
    new ExtractTextPlugin('css/[name].css')
  ]
});