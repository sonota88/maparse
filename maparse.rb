# coding: utf-8

def parse_opts opts_arg
  delim = opts_arg[0]
  body  = opts_arg[1..-1]
  kvs = body.split(delim)
  opts = {}

  kvs.each do |kv|
    idx = kv.index("=")
    # assert idx >= 1
    k = kv[0 ... idx]
    v = kv[(idx + 1) .. -1]
    opts[k] = v
  end

  opts
end

def parse_args args, names
  opts = {}

  if ARGV.size == names.size
    # no optional arguments
  elsif ARGV.size == names.size + 1
    opts = parse_opts(ARGV[names.size])
  else
    raise "invalid arguments size"
  end

  names.each_with_index do |name, i|
    opts[name] = args[i]
  end

  opts
end
