// Helpers
var webpack = require('webpack');
var path = require('path');

// Plugins
var CommonsChunkPlugin = webpack.optimize.CommonsChunkPlugin;
var autoprefixer = require('autoprefixer');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var CopyWebpackPlugin = require('copy-webpack-plugin');
var ForkCheckerPlugin = require('awesome-typescript-loader').ForkCheckerPlugin;

var srcDir = './src';
var targetDir = '../../../target/classes/static';

var webpackConfig = {};

webpackConfig.devtool = "inline-sourcemap";

webpackConfig.entry = {
  'polyfills': srcDir + "/polyfills.ts",
  'vendor': srcDir + "/vendor.ts",
  'main': srcDir + "/main.ts"
};

webpackConfig.output = {
  path: targetDir,
  filename: "js/[name].[hash].js",
  chunkFilename: '[id].[hash].chunk.js'
};
webpackConfig.resolve = {
  // only discover files that have those extensions
  extensions: ['.js', '.ts', '.json', '.css', '.less', '.html']
};

webpackConfig.module = {};

webpackConfig.module.rules = [
  // Support for .ts files.
  {
    test: /\.ts$/,
    loaders: ['awesome-typescript-loader?', 'angular2-template-loader'],
    exclude: [/node_modules\/(?!(ng2-.+))/]
  },
  // copy those assets to output
  {
    test: /\.(png|jpe?g|gif|svg|woff|woff2|ttf|eot|ico)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
    loader: 'file?name=fonts/[name].[hash].[ext]?'
  },
  // Support for *.json files.
  {test: /\.json$/, loader: 'json'},

  // Support for CSS as raw text
  // use 'null' loader in test mode (https://github.com/webpack/null-loader)
  // all css in src/style will be bundled in an external css file
  {
    test: /\.css$/,
    exclude: root('src', 'app'),
    loader: ExtractTextPlugin.extract({fallbackLoader: 'style-loader', loader: ['css?sourceMap', 'postcss']})
  },
  // all css required in src/app files will be merged in js files
  {test: /\.css$/, include: root('src', 'app'), loader: 'raw!postcss'},

  // support for .less files
  // all css in src/style will be bundled in an external css file
  {
    test: /\.less$/,
    exclude: root('src', 'app'),
    loader: ExtractTextPlugin.extract({fallBackLoader: 'style-loader', loader: ['css', 'postcss', 'less']})
  },
  // all css required in src/app files will be merged in js files
  {
    test: /\.less$/, include: root('src', 'app'), loader: ExtractTextPlugin.extract({
    fallBackLoader: 'style-loader',
    loader: ['css', 'postcss','less']})
  },
  // support for .html as raw text
  {test: /\.html$/, loader: 'raw', exclude: root('src', 'public')}
];

// tslint support
webpackConfig.module.rules.push({
  test: /\.ts$/,
  enforce: 'pre',
  loader: 'tslint'
});

webpackConfig.plugins = [
  new ForkCheckerPlugin(),
  // Workaround needed for angular 2 angular/angular#11580
  new webpack.ContextReplacementPlugin(
    // The (\\|\/) piece accounts for path separators in *nix and Windows
    /angular(\\|\/)core(\\|\/)(esm(\\|\/)src|src)(\\|\/)linker/,
    root(srcDir) // location of your src
  ),
  // Tslint configuration for webpack 2
  new webpack.LoaderOptionsPlugin({
    options: {
      /**
       * Apply the tslint loader as pre/postLoader
       * Reference: https://github.com/wbuchwalter/tslint-loader
       */
      tslint: {
        emitErrors: false,
        failOnHint: false
      },
      /**
       * PostCSS
       * Reference: https://github.com/postcss/autoprefixer-core
       * Add vendor prefixes to your css
       */
      postcss: [
        autoprefixer({
          browsers: ['last 2 version']
        })
      ]
    }
  }),
  // Generate common chunks if necessary
  // Reference: https://webpack.github.io/docs/code-splitting.html
  // Reference: https://webpack.github.io/docs/list-of-plugins.html#commonschunkplugin
  new CommonsChunkPlugin({
    name: ['vendor', 'polyfills']
  }),
  // Inject script and link tags into html files
  // Reference: https://github.com/ampedandwired/html-webpack-plugin
  new HtmlWebpackPlugin({
    template: srcDir + '/public/index.html',
    chunksSortMode: 'dependency'
  }),
  // Extract css files
  // Reference: https://github.com/webpack/extract-text-webpack-plugin
  // Disabled when in test mode or not in build mode
  new ExtractTextPlugin('css/[name].[hash].css'),
  // Copy assets from the public folder
  // Reference: https://github.com/kevlened/copy-webpack-plugin
  new CopyWebpackPlugin([{
    from: root(srcDir + '/public')
  }])

];

module.exports = webpackConfig;

// Helper functions
function root(args) {
  args = Array.prototype.slice.call(arguments, 0);
  return path.join.apply(path, [__dirname].concat(args));
}