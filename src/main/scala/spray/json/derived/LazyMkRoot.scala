package spray
package json
package derived

import scala.deriving.*

object LazyMkRoot {
  inline given derived[T](using m: Mirror.Of[T], configuration: Configuration): LazyMkRoot[T] = {
    LazyMkRoot(MkJsonFormatRoot(context => {
      lazy val formats = LazyMk.summonAllFormats[m.MirroredElemTypes](context)
      lazy val labels = LazyMk.summonAllLabels[m.MirroredElemLabels]

      new RootJsonFormat[T] {
        override def read(json: JsValue): T = inline m match {
          case s: Mirror.SumOf[T]     => LazyMk.readCases[T](context, labels, formats)(json)
          case p: Mirror.ProductOf[T] => LazyMk.readElems(p)(labels, formats)(json)
        }
        override def write(obj: T): JsValue = inline m match {
          case s: Mirror.SumOf[T]     => LazyMk.writeCases(s)(context, labels, formats)(obj)
          case p: Mirror.ProductOf[T] => LazyMk.writeElems(configuration, formats)(obj)
        }
      }
    }))
  }
}

case class LazyMkRoot[T](mkJsonFormat: MkJsonFormatRoot[T])
