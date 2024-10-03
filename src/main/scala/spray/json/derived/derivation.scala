/*
 * Copyright 2024 Paolo Boni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package spray
package json
package derived

// workaround to support recursive-types
// see https://github.com/lampepfl/dotty/issues/8183
given lazyDerived[T](using wrapper: => LazyMk[T]): MkJsonFormat[T] = new MkJsonFormat[T](context =>
  new JsonFormat[T] {
    override def read(json: JsValue): T = wrapper.mkJsonFormat.value(context).read(json)
    override def write(obj: T): JsValue = wrapper.mkJsonFormat.value(context).write(obj)
  })

given lazyDerivedRoot[T](using wrapper: => LazyMk[T]): MkJsonFormatRoot[T] = new MkJsonFormatRoot[T](context =>
  new RootJsonFormat[T] {
    override def read(json: JsValue): T = wrapper.mkJsonFormat.value(context).read(json)
    override def write(obj: T): JsValue = wrapper.mkJsonFormat.value(context).write(obj)
  })
